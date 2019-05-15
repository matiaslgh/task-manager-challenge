import React from 'react'
import { connect } from 'react-redux'
import { openCreateTaskDialog } from '../actions/uiAction'
import { Button } from 'material-ui'
import { withStyles } from 'material-ui/styles'
import { Add as AddIcon } from 'material-ui-icons'

const CreateTaskButton = props => {
  const { classes, openDialog } = props
  return (
    <Button variant="fab" color="primary" aria-label="Create a task" className={classes.button} onClick={openDialog}>
      <AddIcon />
    </Button>
  )
}

const styles = () => ({
  button: {
    position: 'fixed',
    bottom: 32,
    right: 32
  }
})

const mapDispatchToProps = dispatch => ({
  openDialog: () => dispatch(openCreateTaskDialog())
})

export default connect(null, mapDispatchToProps)(withStyles(styles)(CreateTaskButton))