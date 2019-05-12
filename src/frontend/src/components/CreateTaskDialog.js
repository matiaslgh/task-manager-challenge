import React, { Component } from 'react'
import { connect } from 'react-redux'
import { openSnackbar } from '../actions/uiAction'
import { closeCreateTaskDialog } from '../actions/uiAction'
import { createTask } from '../utils/apiTasks'
import { TextField, Button } from 'material-ui'
import Dialog, { DialogTitle } from 'material-ui/Dialog'
import { withStyles } from 'material-ui/styles'

const MAX_INT = Math.pow(2,32) / 2

class CreateTaskDialog extends Component {

  state = {
    input: "",
    inputError: false
  }

  isInt = n => /^\d+$/.test(n)

  isValid = n => n.length && this.isInt(n) && n < MAX_INT && n > 0

  handleChange = ({ target }) => {
    const input = target.value
    this.setState({ input, inputError: !this.isValid(input) })
  }

  handleOnClick = () => {
    const { input } = this.state
    const { openSnackbar } = this.props

    if (this.isValid(input)) {
      createTask(parseInt(input, 10)).catch(({ message }) => openSnackbar(message))
      this.closeDialog()
    } else {
      this.setState({ inputError: true })
    }
  }

  closeDialog = () => {
    this.cleanData()
    this.props.closeDialog()
  }

  cleanData = () => {
    this.setState({
      input: "",
      inputError: false
    })
  }

  render() {
    const { classes, isOpen } = this.props
    const { input, inputError } = this.state
    return (
      <Dialog open={isOpen} onClose={this.closeDialog} aria-labelledby="createTaskDialogTitle">
        <DialogTitle id="createTaskDialogTitle">Create a task</DialogTitle>
        <form autoComplete="off" className={classes.form}>
          <TextField
            id="input"
            label="Input"
            error={inputError}
            value={input}
            onChange={this.handleChange}
            className={classes.field}
            margin="normal"
          />
          <span style={ !inputError ? {display: 'none'} : {color: 'red', display: 'block'} }>
            The input must be an integer number between 1 and {MAX_INT}
          </span>
          <Button variant="raised" color="primary" className={classes.button} onClick={this.handleOnClick}>
            Create
          </Button>
        </form>
      </Dialog>
    )
  }
}

const styles = () => ({
  form: {
    padding: 15,
    width: 400,
    textAlign: 'center'
  },
  field: {
    width: 400,
    marginTop: 0
  },
  button: {
    marginTop: 20
  }
})

const mapStateToProps = ({ ui }) => ({
  isOpen: ui.isCreateTaskDialogOpen
})

const mapsDispatchToProps = dispatch => ({
  closeDialog: () => dispatch(closeCreateTaskDialog()),
  openSnackbar: message => dispatch(openSnackbar(message))
})

export default connect(mapStateToProps, mapsDispatchToProps)(withStyles(styles)(CreateTaskDialog))