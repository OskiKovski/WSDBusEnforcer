import React, {Component} from 'react';
import ListPosition from "./ListPosition";
import ListView from "./ListView";

class PoliceListView extends ListView {

    determineContentByType = () => {
        return (
            <div className="list-view">
                <ListPosition key={0} name={'Wydzial ds. zabojstw'} index={0}/>
                <ListPosition key={1} name={'W 11'} index={1}/>
            </div>
        );
    };

    determineTitleByType = () => {
        return 'Jednoski policji';
    };
}

export default PoliceListView;
