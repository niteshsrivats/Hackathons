import React, {Component, Fragment} from "react";
import {Card, Icon, Typography} from "antd";

const {Title} = Typography;

class DriveCard extends Component {
    render() {
        return (
            <Fragment>
                <Card.Grid>
                    {
                        this.props.type === "folder" &&
                        <Card onClick={() => this.props.handleChange(this.props.data.id)} bordered={true}>
                            <Icon type={this.props.type} style={{fontSize: '50px'}}/>
                            <Title level={4}>{this.props.data.name}</Title>
                        </Card>
                    }
                    {
                        this.props.type === "file" &&
                        <Card bordered={true}>
                            <Icon type={this.props.type} style={{fontSize: '50px'}}/>
                            <Title level={4}>{this.props.data.name + this.props.data.extension}</Title>
                        </Card>
                    }
                </Card.Grid>
            </Fragment>
        )
    }
}

export default DriveCard;
