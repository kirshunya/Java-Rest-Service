import styles from './App.module.scss'
import {Link} from "react-router-dom";

export function Home() {
    return <div className={styles.layout}>
        <h1>Home page</h1>
        <img src={'/vite.svg'} width={100} />
        <div>
            <p>pipupu</p>
            <br/>
            <Link to={'/users'}>Пользователи</Link>
        </div>
    </div>
}
