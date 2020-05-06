import React, {Component, Fragment} from 'react';
import {
  AsyncStorage,
  Alert,
  Button,
  View,
  Text,
  TextInput,
  Picker,
} from 'react-native';
import axios from 'axios';

class ExpectationReport extends Component {
  state = {name: '', phoneNumber: '', type: 'SUGGESTION', description: ''};

  submit = async () => {
    const {navigate} = this.props.navigation;
    const {name, phoneNumber, description, type} = this.state;
    const beatReportId = await AsyncStorage.getItem('beatToken');

    const obj = {
      name,
      phoneNumber,
      description,
      beatExpectationReportType: type
    }

    console.log(obj, beatReportId);

    axios
      .post(
        `http://172.16.8.174:8080/v1/beat-reports/${beatReportId}/expectations`,
        obj,
      )
      .then(response => {
        Alert.alert('Successfully recorded response');
        navigate('Home');
      })
      .catch(err => {
        console.log(err);
        Alert.alert('Could not submit form');
      });
  };

  render() {
    return (
      <Fragment>
        <View style={{flex: 1, padding: 20}}>
          <Text style={{textAlign: 'center', fontWeight: 'bold'}}>
            Expectation Report
          </Text>
          <TextInput
            style={{
              height: 40,
              borderColor: 'gray',
              borderWidth: 1,
              marginVertical: 20,
            }}
            onChangeText={name => this.setState({name})}
            value={this.state.name}
            placeholder="Enter name"
          />
          <TextInput
            style={{
              height: 40,
              borderColor: 'gray',
              borderWidth: 1,
              marginVertical: 20,
            }}
            onChangeText={phone => this.setState({phone})}
            value={this.state.phone}
            placeholder="Enter phone number"
          />
          <Picker
            selectedValue={this.state.type}
            style={{height: 50, width: 300, marginVertical: 20}}
            onValueChange={(itemValue, itemIndex) =>
              this.setState({type: itemValue})
            }>
            <Picker.Item label="Suggestion" value="SUGGESTION" />
            <Picker.Item label="Complaint" value="COMPLAINT" />
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

export default ExpectationReport;
