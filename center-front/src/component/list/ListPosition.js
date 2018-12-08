import React, {Component} from 'react';


class ListPosition extends Component {

    constructor(){
        super();
		this.hoverOn = this.hoverOn.bind(this);
		this.hoverOff = this.hoverOff.bind(this);
    }
	
	hoverOn() {
		this.props.hoverHandler(this.props.id);
	}
	
	hoverOff() {
		this.props.hoverHandler(null);
	}

    render() {
        return (
			<tr
				className={this.props.active ? "table-active" : ""}
				onMouseEnter={this.hoverOn}
				onMouseLeave={this.hoverOff}>
				<th scope="row">{this.props.index}</th>
                <td>{this.props.name}</td>
            </tr>
        );
    }
}

export default ListPosition;

