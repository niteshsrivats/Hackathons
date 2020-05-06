import React, {Component, Fragment} from "react";
import {Button, Card} from "antd";

class ProcessCard extends Component {

    gridStyle = {
        minWidth: '250px',
        width: '25%',
        textAlign: 'center',
        background: '#F0F2F5'
    };

    render() {
        return <Fragment>
            <Card.Grid style={this.gridStyle}>
                <Card title={this.props.data.name} bordered={true}
                >
                    {/*<p>Name: {cards.name}</p>*/}
                    {/*<p>Year: {}</p>*/}
                    <Button type="primary" href={`http://localhost:3000/process/${this.props.data.id}`} block>
                        View Progress
                    </Button>
                </Card>
            </Card.Grid>
        </Fragment>
    }
}

export default ProcessCard;
