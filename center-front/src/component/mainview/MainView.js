import React, {Component} from 'react';
import CityMap from "./../citymap/CityMap";
import BusListView from "../list/BusListView";
import PoliceListView from "../list/PoliceListView";
import MockAdapter from "axios-mock-adapter";
import axios from "axios";
import {url} from "../Constants.js"

class MainView extends Component {

    constructor(){
        super();
        this.state = {
			buses: [],
			policeCars: [],
			hovered: null
		};

        //this.setMock();
		this.hoverHandler = this.hoverHandler.bind(this);
        this.fetchUpdate = this.fetchUpdate.bind(this);
        this.fetchUpdate();
        setInterval(this.fetchUpdate, 5000);
    }

    setMock() {
        const data = {
            buses: [
                {
                    id: '523',
                    name: 'Autobus 523',
                    position: [52.253354, 20.996054]
                },
                {
                    id: '162',
                    name: 'Autobus 162',
                    position: [52.252106, 20.998135]
                },
                {
                    id: '20',
                    name: 'Tramwaj 20',
                    position: [52.253866, 21.000045]
                }
            ],
            policeCars: [
                {
                    id: '007',
                    name: '007',
                    position: [52.255094, 20.997438]
                },
                {
                    id: 'W11',
                    name: 'W 11',
                    position: [52.254923, 20.995979]
                }
            ]
        };

        new MockAdapter(axios).onGet(url).reply(200, data);
    }

    async fetchUpdate() {
        axios.get(url)
            .then(response => {
                this.setState(this.createState(response));
            })
    }
	
	createState(response) {
		let state = {
			buses: response.data.buses,
			policeCars: response.data.policeCars,
			hovered: this.state.hovered
			
		};
		return state;
	}
	
	hoverHandler(hovered) {
		this.setState(
			{...this.state, hovered: hovered}
		);
	}

    render() {
        return (
		<div className="container-fluid">
			<h1>Command Center</h1>
			<div className="row">
				<div className="col-lg-6">
					<CityMap data={this.state} hoverHandler={this.hoverHandler}/>
				</div>
				<div className="col-lg-5">
					<div className="row">
						<div className="col-md-6">
							<BusListView type={'BUS'} data={this.state.buses} hovered={this.state.hovered} hoverHandler={this.hoverHandler}/>
						</div>
						<div className="col-md-6">
							<PoliceListView type={'POLICE'} data={this.state.policeCars} hovered={this.state.hovered} hoverHandler={this.hoverHandler}/>
						</div>
					</div>
				</div>
			</div>
		</div>
        )
    }
}

export default MainView;
