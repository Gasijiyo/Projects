const express = require("express");
const app = express();
const dotenv = require("dotenv");   //.env는 보안으로 gitignore처리. (mongo의 cluster)
const mongoose = require("mongoose")
const authRoute = require("./routes/auth")
const userRoute = require("./routes/users")
const postRoute = require("./routes/posts")
const categoryRoute = require("./routes/categories")
const multer = require("multer");   // image 업로드용

dotenv.config();
app.use(express.json());    // 요청 응답 시 사용할 json형식을 위해 선언

mongoose
    .connect(process.env.Mongo_url) //env에 설정한 변수값을 입력
    .then(console.log("Connected to MongoDB"))  // 연결 성공 시의 log
    .catch(err => console.log(err));    // 연결 실패 시의 log

    const storage = multer.diskStorage({
        destination:(req,file,cb) => { //callback
            cb(null, "images")
        }, filename:(req, file, cb) => {
            cb(null, req.body.name);
        },
    })

    const upload = multer({storage:storage})
    app.post("/api/upload", upload.single("file"), (req,res) => {
        res.status(200).json("The file has been uploaded");
    });


app.use("/api/auth", authRoute);    // ROUTE(주소) 설정 
app.use("/api/users", userRoute);
app.use("/api/posts", postRoute);
app.use("/api/categories", categoryRoute);

app.listen("5000", ()=>{    // port번호 설정
    console.log('Backend is running');
})