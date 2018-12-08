import React, {Component} from 'react';
import ListPosition from "./ListPosition";

class ListView extends Component {

    componentWillMount() {
        this.type = this.props.type;
    }

    determineListContent = () => {
        let j = 0;
        return (
            this.props.data.map(elem => {
                j++;
                return <ListPosition
					index={j}
					id={elem.id}
					name={elem.name}
					active={this.props.hovered === elem.id}
					hoverHandler={this.props.hoverHandler} />
            })
        );
    };

    render() {
        return (
			<table className="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>{this.determineTitleByType()}</th>
					</tr>
				</thead>
				<tbody>
					{this.determineListContent()}
				</tbody>
			</table>
        );
    }
}

export default ListView;
