import React, {Component} from 'react';

class ListView extends Component {

    componentWillMount() {
        this.type = this.props.type;
    }

    render() {
        return (
            <div>
                <div className="list-view-title">
                    {this.determineTitleByType()}
                </div>

                {this.determineContentByType()}
            </div>
        );
    }
}

export default ListView;
