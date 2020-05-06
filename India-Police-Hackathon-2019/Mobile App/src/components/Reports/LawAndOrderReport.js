import React, {Component, Fragment} from 'react';
import {
  Alert,
  AsyncStorage,
  Button,
  View,
  Text,
  TextInput,
  Picker,
} from 'react-native';
import axios from 'axios';

class LawAndOrderReport extends Component {
  state = {type: 'COMMUNAL', description: ''};
  // types: communal, local
  submit = async () => {
    const {navigate} = this.props.navigation;
    const {type, description} = this.state;

    const beatReportId = await AsyncStorage.getItem('beatToken');
    const obj = {
      description,
      beatLawOrderIssueType: type,
    };
    axios
      .post(
        `http://172.16.8.174:8080/v1/beat-reports/${beatReportId}/law-order`,
        obj,
      )
      .then(response => {
        Alert.alert('Successfully recorded response');
        navigate('Home');
      })
      .catch(err => { 
        console.log(err)
        Alert.alert('Could not submit form')});
  };

  render() {
    return (
      <Fragment>
        <View style={{flex: 1, padding: 20}}>
          <Text style={{textAlign: 'center', fontWeight: 'bold'}}>
            Law and Order Report
          </Text>
          <Picker
            selectedValue={this.state.type}
            style={{height: 50, width: 300, marginVertical: 20}}
            onValueChange={(itemValue, itemIndex) =>
              this.setState({type: itemValue})
            }>
            <Picker.Item label="Communal" value="COMMUNAL" />
            <Picker.Item label="Local" value="LOCAL" />
          </Picker>
          <TextInput
            style={{
              height: 40,
              borderColor: 'gray',
              borderWidth: 1,
              marginVertical: 20,
            }}
            onChangeText={description => this.setState({description})}
            value={this.state.description}
            placeholder="Enter description"
          />
          <Button onPress={this.submit} title="Submit" />
        </View>
      </Fragment>
    );
  }
}

export default LawAndOrderReport;
