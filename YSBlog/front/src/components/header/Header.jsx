import "./header.css"

export default function Header() {
  return (
    <div className='header'>
        <div className="headerTitles">
            <span className="headerTitleSm">React & Node.js</span>
            <span className="headerTitleLg">Blog</span>
        </div>
        <img className="headerImg" src="view3.png" alt=""/>
    </div>
  )
}
