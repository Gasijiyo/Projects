
// Canvas Settings
let canvas;
let ctx;
canvas = document.createElement("canvas")
ctx = canvas.getContext("2d")
canvas.width=400;
canvas.height=800;
document.body.appendChild(canvas);

let backgroundImage, fighterImage, bulletImage, enemyImage, gameOverImage;

// 우주선 좌표
let fighterX = canvas.width/2 - 32;
let fighterY = canvas.height - 64;

// 총알 좌표
let bulletList = [] // 총알저장배열
function Bullet(){
    this.x = 0;
    this.y = 0;
    this.init = function(){
        this.x = fighterX + 20
        this.y = fighterY - 15
        bulletList.push(this);
    };
    this.update = function(){
        this.y -= 10;
    };

}

// 이미지로딩
function loadImage() {
    backgroundImage = new Image();
    backgroundImage.src="resources/images/kmjd.png";

    fighterImage = new Image();
    fighterImage.src="resources/images/fighter.png";

    bulletImage = new Image();
    bulletImage.src="resources/images/bullet.png";

    enemyImage = new Image();
    enemyImage.src="resources/images/enemy.png";

    gameOverImage = new Image();
    gameOverImage.src="resources/images/gameover.png";
}

// 방향키 입력 (addEventListener)
let keysDown = {};
function setupKeyboardListener() {
    document.addEventListener("keydown", function (event) {
        keysDown[event.keyCode] = true;
        console.log("key: " , keysDown);
    });
    document.addEventListener("keyup", function(event){
        delete keysDown[event.keyCode];
        // spacebar = 32 
        // spacebar 무한 발사방지 
        if (event.keyCode == 32) {
            createBullet();
        }        
    });
}

// 총알 생성
function createBullet(){
    let b = new Bullet();
    b.init();
    console.log(bulletList);
}

//적군 생성 1초당 하나
function createEnemy(){
    const interval = setInterval(function(){
        let e = new Enemy()
        e.init()
    }, 1000)
}

// 방향키 값 변경
function update(){
    if(39 in keysDown) {
        fighterX += 3;
    } // arrow right

    if(37 in keysDown) {
        fighterX += -3;
    } //arrow left

    if(38 in keysDown) {
        fighterY += -3;
    } //arrow up

    if(40 in keysDown) {
        fighterY += 3;
    } //arrow down

    //X좌표값 초과 불가 설정
    if(fighterX <= 0){
        fighterX = 0;        
    } else if (fighterX >= canvas.width-64) {
        fighterX = canvas.width-64;        
    }
    //Y좌표값 초과 불가 설정
    if (fighterY <= canvas.height-128) {
        fighterY = canvas.height-128;        
    } else if (fighterY >= canvas.height-40) {
        fighterY = canvas.height-40;        
    }

    // 총알의 y좌표 업데이트 함수호출
    for (let i = 0; i < bulletList.length; i++){
        bulletList[i].update();
    }

    // 적군의 y좌표 업데이트
    for (let i = 0; i < enemyList.length; i++){
        enemyList[i].update();
    }
}

// 적군좌표 범위 내 랜덤부여
function generateRandomValue(min, max){
    let randomNum = Math.floor(Math.random()*(max-min+1));
    return randomNum
}

// 적군 좌표설정
let enemyList = []
function Enemy() {
    this.x = 0;
    this.y = 0;
    this.init = function(){
        this.y = 0;
        this.x = generateRandomValue(0, canvas.width-32);
        enemyList.push(this);
    }
    this.update=function(){
        this.y += 1;
    }

}

function render() {
    ctx.drawImage(backgroundImage, 0, 0, canvas.width, canvas.height);
    ctx.drawImage(fighterImage, fighterX, fighterY);
    
    for (let i = 0; i < bulletList.length;i++){
        ctx.drawImage(bulletImage, bulletList[i].x, bulletList[i].y);
    }

    for (let i = 0; i < enemyList.length; i++){
        ctx.drawImage(enemyImage, enemyList[i].x, enemyList[i].y)
    }

}

function main() {    
    // 방향키 누르면 우주선의 xy좌표 바뀌고 다시 render
    // bullet도 마찬가지
    update();
    render();
    console.log("animation calls main function")
    // 계속 로딩하는 기능
    requestAnimationFrame(main);
}




loadImage();
setupKeyboardListener();
createEnemy();
main();

/** 
 * 총알 만들기
 * 1. SpaceBar 총알발사
 * 2. 총알의 발사 = 총알의 y값이 -- (감소) x값은 spacebar누른 순간 우주선의 x값
 * 3. 발사된 총알들은 총알 배열에 저장을 한다.
 * 4. 총알들은 xy좌표값이 있어야한다.
 * 5. 총알의 배열을 가지고 render 그려줌
 * */ 

/**
 * 
 * 
 * 
 */
