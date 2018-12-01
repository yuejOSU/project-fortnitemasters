var isSetup = true;
var placedShips = 0;
var game;
var shipType;
var vertical;
var sonarAttempt = false;
var numSonars = 2;


function makeGrid(table, isPlayer) {
    for (i=0; i<10; i++) {
        let row = document.createElement('tr');
        for (j=0; j<10; j++) {
            let column = document.createElement('td');
            column.addEventListener("click", cellClick);
            row.appendChild(column);
        }
        table.appendChild(row);
    }
}



function markHits(board, elementId, surrenderText) {
    board.attacks.forEach((attack) => {
        let className;
        if (attack.result === "MISS")
            className = "miss";
        else if (attack.result === "HIT")
            className = "hit";
        else if (attack.result === "SUNK")
            className = "hit";
        else if (attack.result === "SURRENDER")
            alert(surrenderText);

        document.getElementById(elementId).rows[attack.location.row-1].cells[attack.location.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add(className);
    });
}

function redrawGrid() {
    Array.from(document.getElementById("opponent").childNodes).forEach((row) => row.remove());
    Array.from(document.getElementById("player").childNodes).forEach((row) => row.remove());
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);
    if (game === undefined) {
        return;
    }

    game.playersBoard.ships.forEach((ship) => ship.occupiedSquares.forEach((square) => {
        document.getElementById("player").rows[square.row - 1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("occupied");
    }));


    // in addition to drawing occupied ships on board, we need to account for sonar squares (basically copy)
    game.opponentsBoard.sonars.forEach((square) => {
        document.getElementById("opponent").rows[square.row - 1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("gray");
    });

    // looks within the sonar squares and sees if there are any occupied squares
    game.opponentsBoard.ships.forEach((ship) => ship.occupiedSquares.forEach((square) => game.opponentsBoard.sonars.forEach((sonar) => {
        // if there really is an occupied square, we remove the gray color and add the CSS listed color for "occupied"
        if(square.row == sonar.row && (square.column.charCodeAt(0) - 'A'.charCodeAt(0)) == (sonar.column.charCodeAt(0) - 'A'.charCodeAt(0))) {
            document.getElementById("opponent").rows[square.row - 1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.remove("gray");
            document.getElementById("opponent").rows[square.row - 1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("occupied");
        }
    })));

    markHits(game.opponentsBoard, "opponent", "You won the game");
    markHits(game.playersBoard, "player", "You lost the game");

}

var oldListener;
function registerCellListener(f, b) {
    let el = document.getElementById(b);
    for (i=0; i<10; i++) {
        for (j=0; j<10; j++) {
            let cell = el.rows[i].cells[j];
            cell.removeEventListener("mouseover", oldListener);
            cell.removeEventListener("mouseout", oldListener);
            cell.addEventListener("mouseover", f);
            cell.addEventListener("mouseout", f);
        }
    }
    oldListener = f;
}



function cellClick() {
    let row = this.parentNode.rowIndex + 1;
    let col = String.fromCharCode(this.cellIndex + 65);
    if (isSetup) {
        sendXhr("POST", "/place", {game: game, shipType: shipType, x: row, y: col, isVertical: vertical}, function(data) {
            game = data;
            redrawGrid();
            placedShips++;
            if (placedShips == 3) {
                isSetup = false;
                registerCellListener((e) => {});
            }
        });
    }
    else if (sonarAttempt) {
        // once we know we are using the sonar, we can switch it back to false for later use
        sonarAttempt = false;
        sendXhr2("POST", "/sonar", {game: game, x: row, y: col, numSonars: numSonars}, function(data) {
            numSonars--;
            game = data;
            redrawGrid();
        });
    }
    else {
        sendMiss("POST", "/attack", {game: game, x: row, y: col}, function(data) {
            game = data;
            redrawGrid();
        })
    }
}

function sonar() {

    return function() {

        let row = this.parentNode.rowIndex;
        let col = this.cellIndex;
        let r = 2;
        let grid = document.getElementById("opponent");
        let cells = [];

        // for loop to create the circular shape for the sonar weapon
        for(let i = 0; i <= r; i++) {
            if (grid.rows[row + i] !== undefined) {
                cells.push(grid.rows[row + i].cells[col]);
            }
            if (grid.rows[row - i] !== undefined) {
                cells.push(grid.rows[row - i].cells[col]);
            }
            if (grid.rows[row + 1] !== undefined) {
                cells.push(grid.rows[row + 1].cells[col - 1]);
                cells.push(grid.rows[row + 1].cells[col + 1]);
            }
            if (grid.rows[row - 1] !== undefined) {
                cells.push(grid.rows[row - 1].cells[col - 1]);
                cells.push(grid.rows[row - 1].cells[col + 1]);
            }
            cells.push(grid.rows[row].cells[col]);
            cells.push(grid.rows[row].cells[col - i]);
            cells.push(grid.rows[row].cells[col + i]);
        }

            // toggles hiding and showing cells
            cells.forEach(function(e) {
                if(e !== undefined) {
                    e.classList.toggle("placed");
                }
            });
            sonarAttempt = true;

    }

}



function sendXhr(method, url, data, handler) {
    var req = new XMLHttpRequest();
    req.addEventListener("load", function(event) {
        if (req.status != 200) {
            alert("You are either out of bounds or placing a ship you already have. Please try again.");
            return;
        }
        handler(JSON.parse(req.responseText));
    });
    req.open(method, url);
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify(data));
}

function sendXhr2(method, url, data, handler) {

    var req = new XMLHttpRequest();
    req.addEventListener("load", function(event) {
        if (req.status != 200) {
            redrawGrid();
        }
        handler(JSON.parse(req.responseText));
    });
    req.open(method, url);
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify(data));

}

//test
function sendErr(method, url, data, handler) {
    var req = new XMLHttpRequest();
    req.addEventListener("load", function(event) {
        if (req.status != 200) {
            alert("This ship is already used. Place a new ship!");
            return;
        }
        handler(JSON.parse(req.responseText));
    });
    req.open(method, url);
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify(data));
}

function sendMiss(method, url, data, handler) {
    var req = new XMLHttpRequest();
    req.addEventListener("load", function(event) {
        if (req.status != 200) {
            alert("This is a shot you have already attempted. Fire somewhere else.");
            return;
        }
        handler(JSON.parse(req.responseText));
    });
    req.open(method, url);
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify(data));
}
//end Test
function place(size) {
    return function() {
        let row = this.parentNode.rowIndex;
        let col = this.cellIndex;
        vertical = document.getElementById("is_vertical").checked;
        let table = document.getElementById("player");
        for (let i=0; i<size; i++) {
            let cell;
            if(vertical) {
                let tableRow = table.rows[row+i];
                if (tableRow === undefined) {
                    // ship is over the edge; let the back end deal with it
                    break;
                }
                cell = tableRow.cells[col];
            } else {
                cell = table.rows[row].cells[col+i];
            }
            if (cell === undefined) {
                // ship is over the edge; let the back end deal with it
                break;
            }
            cell.classList.toggle("placed");
        }
    }
}

function initGame() {
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);
    document.getElementById("place_minesweeper").addEventListener("click", function(e) {
        shipType = "MINESWEEPER";
       registerCellListener(place(2), "player");
    });
    document.getElementById("place_destroyer").addEventListener("click", function(e) {
        shipType = "DESTROYER";
       registerCellListener(place(3), "player");
    });
    document.getElementById("place_battleship").addEventListener("click", function(e) {
        shipType = "BATTLESHIP";
       registerCellListener(place(4), "player");
    });
    document.getElementById("SONAR").addEventListener("click", function(e) {
        registerCellListener(sonar(), "opponent");
    });
    sendErr("GET", "/game", {}, function(data) {
        game = data;
    });
};