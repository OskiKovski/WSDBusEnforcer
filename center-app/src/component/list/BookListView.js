import React, {Component} from 'react';
import BookListPosition from "./BookListPosition";

class BookListView extends Component {

    constructor(){
        super();
    }

    render() {
        const type = this.props.type;

        if(type === 'BUS'){
            return (
                <div className="list-view">
                    <BookListPosition key={0} name={'Autobus 523'} index={0}/>
                    <BookListPosition key={1} name={'Autobus 160'} index={1}/>
                </div>
            );
        }
        else if(type === 'POLICE'){
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
    }
}

export default BookListView;
