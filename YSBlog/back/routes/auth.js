const router = require("express").Router();
const User = require("../models/User");
const bcrypt = require("bcrypt");

//Register
// uptate => put, delete => delete, get => get
router.post("/register", async (req, res) => {
  try {
    // 비밀번호를 해시함수로 변환해 암호화
    const salt = await bcrypt.genSalt(10);
    const hashedPass = await bcrypt.hash(req.body.password, salt);

    const newUser = new User({
      username: req.body.username,
      email: req.body.email,
      password: hashedPass,
    });

    const user = await newUser.save();
    res.status(200).json(user);
  } catch (err) {
    res.status(500).json(err);
  }
});

//Login
router.post("/login", async (req, res) => {
  try {
    // 아이디 또는 비밀번호 일치여부 확인  
    const user = await User.findOne({ username: req.body.username });
    if (!user) {
      return res.status(400).json("Wrong name!");
    }

    const validated = await bcrypt.compare(req.body.password, user.password);
    if (!validated) {
      return res.status(400).json("Wrong password!");
    }
    // const user = await User.findOne({ username: req.body.username });
    // !user && res.status(400).json("Wrong credentials!");
    // const validated = await bcrypt.compare(req.body.password, user.password);
    // !validated && res.status(400).json("Wrong credentials!");
    // res.status(200).json(user);


    //DB에서 비밀번호를 제외한 프로퍼티만 가져오기
    const {password, ...others} = user._doc;
    res.status(200).json(others);
    // const ab = user._doc;
    // res.status(200).json(ab);   
  } catch (err) {
    res.status(500).json(err);
  }
});

module.exports = router;
