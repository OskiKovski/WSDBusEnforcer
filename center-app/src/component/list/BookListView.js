import React, {Component} from 'react';
import BookListPosition from "./BookListPosition";

class BookListView extends Component {

    constructor(){
        super();
    }

    componentWillMount() {
        this.type = this.props.type;
    }

    determineContentByType = () => {
        if(this.type === 'BUS'){
            return (
                <div className="list-view">
                    <BookListPosition key={0} name={'Autobus 523'} index={0}/>
                    <BookListPosition key={1} name={'Autobus 160'} index={1}/>
                </div>
            );
        }
        else if(this.type === 'POLICE'){
            return (
                <div className="list-view">
                    <BookListPosition key={0} name={'Wydzial ds. zabojstw'} index={0}/>
                    <BookListPosition key={1} name={'W 11'} index={1}/>
                </div>
            );
        }
        else {
            return <div />
        }
    };

    determineTitleByType = () => {
        if(this.type === 'BUS'){
            return 'Autobusy';
        }
        else if(this.type === 'POLICE'){
            return 'Jednoski policji';
        }
        else {
            return '';
        }
    };

    render() {
        return <div>
            <div className="list-view-title">
                {this.determineTitleByType()}
            </div>

            {this.determineContentByType()}
        </div>
    }
}

export default BookListView;
