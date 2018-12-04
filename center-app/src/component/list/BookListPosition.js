import React, {Component} from 'react';


class BookListPosition extends Component {

    constructor(){
        super();
        this.state = {
            response: []
        };
    }

    determineStyle = () => {
        return this.props.index % 2 ? 'book-list-pos1' : 'book-list-pos2';
    };

    entry = () => {
        return (
            <div>
                <div className="book-list-pos-section1">
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

export default BookListPosition;

