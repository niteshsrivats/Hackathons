import React, {Component, Fragment} from "react";
import {Button, Card} from "antd";

class WorkflowCard extends Component {

    gridStyle = {
        minWidth: '250px',
        width: '25%',
        textAlign: 'center',
        background: '#F0F2F5'
    };

    render() {
        if (this.props.data) {
            return (
                <Card.Grid style={this.gridStyle}>
                    <Card title={this.props.data.name} bordered={true}>
                        <Button type="primary" href={`http://localhost:3000/workflow/${this.props.data.id}`} block>
                            Go to Process
                        </Button>
                    </Card>
                </Card.Grid>
            );
        } else {
            return <Fragment/>
        }
    }
}

export default WorkflowCard;
