import React, {Component} from 'react';
import CityMap from "./../citymap/CityMap";
import BookListView from "../list/BookListView";

class MainView extends Component {

    constructor(){
        super();
    }

    render() {
        return (
            <section className="side-by-side-container">
                <div className="map-section">
                    <CityMap/>
                </div>
                <div className="list-container-section">
                    <section className="lists-container">
                        <div className="left-list-section">
                            <BookListView type={'BUS'}/>
                        </div>
                        <div className="right-list-section">
                            <BookListView type={'POLICE'}/>
                        </div>
                    </section>
                </div>
            </section>


        )
    }
}

export default MainView;
