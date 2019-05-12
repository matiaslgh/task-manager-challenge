import React, { Component } from 'react'
import { connect } from 'react-redux'
import { getTasks, createOrUpdateTask } from '../actions/tasksAction'
import { getStatuses } from '../utils/apiTasks'
import Header from './Header'
import CreateTaskDialog from './CreateTaskDialog'
import CreateTaskButton from './CreateTaskButton'
import StatusColum from './StatusColum'
import { withStyles } from 'material-ui/styles'
import { connectWebSocket } from '../utils/webSocket'
import Snackbar from './Snackbar'

class App extends Component {

  state = {
    statuses: []
  }

  componentDidMount() {
    getStatuses().then(statuses => this.setState({ statuses }))
    this.props.updateTasksList()
    this.configureWebSocket()
  }

  configureWebSocket = () => {
    const stompClient = connectWebSocket()
    stompClient.debug = false
    stompClient.connect({}, () => {
      stompClient.subscribe('/topic/taskUpdate', ({ body }) => {
        this.props.createOrUpdateTask(JSON.parse(body))
      })
    })
  }

  render() {
    const { statuses } = this.state
    const { classes } = this.props
    return (
      <div>
        <Header />
        <div className={classes.statusColums}>
          { statuses.map( status => (
            <StatusColum key={status.id} statusFilter={status.id} title={status.text}/>
          ))}
        </div>
        <CreateTaskDialog />
        <CreateTaskButton />
        <Snackbar />
      </div>
    )
  }
}

const styles = () => ({
  statusColums: {
    display: 'flex',
    margin: 55,
    textAlign: 'center'
  }
})

const mapDispatchToProps = dispatch => ({
  updateTasksList: () => dispatch(getTasks()),
  createOrUpdateTask: task => dispatch(createOrUpdateTask(task))
})

export default connect(null, mapDispatchToProps)(withStyles(styles)(App))
