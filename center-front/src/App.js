import React, {Component} from 'react';
import './index.css';
import MainView from "./component/mainview/MainView";

class App extends Component {
    render() {
        return(
            <div>
                <h1 className="main-title">Command Center</h1>
                <MainView/>
            </div>
        );
    }
}

export default App;
