import "./post.css"

export default function Post({img}) {
  return (
    <div className="post">
        <img className="postImg" 
        src={img} alt="" />
        <div className="postInfo">
            <div className="postCats">
                <span className="postCat">Music</span>
                <span className="postCat">Life</span>
            </div>            
            <span className="postTitle">
                Lorem ipsum dolor sit amet
            </span>
            <hr />
            <span className="postDate">1 hour ago</span>
            
        </div>
        <p className="postDesc">
            Yesterday
            All my trouble seemed so far away
            Now it looks as though they're here to stay
            Oh, I believe in yesterday
            Suddenly
            I'm not half the man I used to be
            There's a shadow hanging over me
            Oh, yesterday came suddenly
            Why she had to go, I don't know
            She wouldn't say
            I said something wrong
            Now I long for yesterday
        </p>
    </div>
  )
}
