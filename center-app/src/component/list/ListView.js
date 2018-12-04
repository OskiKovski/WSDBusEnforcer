import React, {Component} from 'react';
import ListPosition from "./ListPosition";

class ListView extends Component {

    componentWillMount() {
        this.type = this.props.type;
    }

    determineListContent = () => {
        let j = 0;
        return (
            this.props.data.map(i => {
                j++;
                return <ListPosition key={j} name={i} index={j}/>
            })
        );
    };

    render() {
        return (
            <div>
                <div className="list-view-title">
                    {this.determineTitleByType()}
                </div>

                {this.determineListContent()}
            </div>
        );
    }
}

export default ListView;
