import React, {Component} from 'react';
import ListPosition from "./ListPosition";
import ListView from "./ListView";

class BusListView extends ListView {

    determineContentByType = () => {
        return (
            <div className="list-view">
                <ListPosition key={0} name={'Autobus 523'} index={0}/>
                <ListPosition key={1} name={'Autobus 160'} index={1}/>
                <ListPosition key={2} name={'Autobus 000'} index={2}/>
                <ListPosition key={3} name={'Autobus 222'} index={3}/>
            </div>
        );
    };

    determineTitleByType = () => {
        return 'Autobusy';
    };
}

export default BusListView;
