import React, {Component} from 'react';


class ListPosition extends Component {

    constructor(){
        super();
        this.state = {
            response: []
        };
    }

    determineStyle = () => {
        return this.props.index % 2 ? 'list-pos1' : 'list-pos2';
    };

    entry = () => {
        return (
            <div>
                <div className="list-pos-section1">
                    <div>
                        <div>
                            <b>{this.props.name}</b>
                        </div>
                    </div>
                </div>
            </div>
        )
    };

    render() {
        return (
            <div className={this.determineStyle()}>
                {this.entry()}
            </div>
        );
    }
}

export default ListPosition;

