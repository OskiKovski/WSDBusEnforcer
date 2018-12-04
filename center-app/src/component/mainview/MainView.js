import React, {Component} from 'react';

class MainView extends Component {

    constructor(){
        super();
        this.state = {
            searchValue: ''
        };
    }

    updateSearchInput = (evt) => {
        this.setState({
            searchValue: evt.target.value
        });
    };

    render() {
        return (
            <div className="header">
                LolxD
            </div>
        )
    }
}

export default MainView;
