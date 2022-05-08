const router = require("express").Router();
const User = require("../models/User");
const Post = require("../models/Post");
const bcrypt = require("bcrypt");

//Update user info
router.put("/:id", async (req, res) => {
  if (req.body.userId === req.params.id) {
    //단순히 현재 아이디값과 수정하려는 아이디값이 일치할 때만 업데이트 가능하게 설정
    if (req.body.password) {
      //비밀번호 업데이트를 위한 선언부분
      const salt = await bcrypt.genSalt(10);
      req.body.password = await bcrypt.hash(req.body.password, salt);
    } // 새로운 비밀번호 입력 및 비밀번호 암호화,
    try {
      const updatedUser = await User.findByIdAndUpdate(
          // Id값으로 찾고 새로운 값으로 업데이트(메소드 설명 확인바람)
        req.params.id,
        {          
          $set: req.body, // 요청값 모두를 적용
        },
        { new: true }
      ); //put 요청 후 결과값 바로 반영
      res.status(200).json(updatedUser);
    } catch (err) {
      res.status(500).json(err);
    }
  } else {
    res.status(401).json("You can only update your account");
  }
});

//Delete user account
router.delete("/:id", async (req, res) => {
    if (req.body.userId === req.params.id) {      
        // 삭제한 사용자의 모든 작성글 삭제
        try {
            const user = await User.findById(req.params.id);
      
            try {
                await Post.deleteMany({username:user.username});
                await User.findByIdAndDelete(req.params.id)
                res.status(200).json("User has been deleted");
            } catch (err) {
                res.status(500).json(err);
            }
    
        } catch(err){
            res.status(404).json("User not found")
        }

    } else {
      res.status(401).json("You can only delete your account");
    }
  });


//Get User
router.get("/:id", async (req, res) => {
    try {
        const user = await User.findById(req.params.id);
        const {password, ...others} = user._doc
        res.status(200).json(others);
    } catch(err){
        res.status(500).json(err)
    }
})


module.exports = router;
