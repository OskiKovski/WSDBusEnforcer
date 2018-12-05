import React, {Component} from 'react';
import CityMap from "./../citymap/CityMap";
import BusListView from "../list/BusListView";
import PoliceListView from "../list/PoliceListView";
import MockAdapter from "axios-mock-adapter";
import axios from "axios";

class MainView extends Component {

    constructor(){
        super();
        this.state = {
            response: {
                buses: [],
                policeCars: [],
            }
        };

        this.setMock();

        this.url = 'http://localhost:8080/api/center/getLists';
        this.fetchUpdate = this.fetchUpdate.bind(this);
        this.fetchUpdate();
        setInterval(this.fetchUpdate, 5000);
    }

    setMock() {
        const data = {
            buses: [
                {
                    id: '1',
                    name: 'Autobus 523',
                    position: [52.253354, 20.996054]
                },
                {
                    id: '2',
                    name: 'Autobus 162',
                    position: [52.252106, 20.998135]
                },
                {
                    id: '3',
                    name: 'Tramwaj 20',
                    position: [52.253866, 21.000045]
                }
            ],
            policeCars: [
                {
                    id: '4',
                    name: 'Komisarz Alex',
                    position: [52.255094, 20.997438]
                },
                {
                    id: '5',
                    name: 'W 11',
                    position: [52.254923, 20.995979]
                }
            ]
        };

        new MockAdapter(axios).onGet(this.url).reply(200, data);
    }

    async fetchUpdate() {
        axios.get(this.url)
            .then(response => {
                this.setState({
                    response: response.data
                });
            })
    }

    render() {
        return (
            <section className="side-by-side-container">
                <div className="map-section">
                    <CityMap data={this.state.response}/>
                </div>
                <div className="list-container-section">
                    <section className="lists-container">
                        <div className="left-list-section">
                            <BusListView type={'BUS'} data={this.state.response.buses}/>
                        </div>
                        <div className="right-list-section">
                            <PoliceListView type={'POLICE'} data={this.state.response.policeCars}/>
                        </div>
                    </section>
                </div>
            </section>
        )
    }
}

export default MainView;
