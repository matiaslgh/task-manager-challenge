import React from 'react'
import { connect } from 'react-redux'
import { openSnackbar } from '../actions/uiAction'
import { withStyles } from 'material-ui/styles'
import Card, { CardContent } from 'material-ui/Card'
import { Button, Typography } from 'material-ui'
import { runTask } from '../utils/apiTasks'

const Task = props => {
  const { task, classes, openSnackbar } = props

  const run = id => {
    runTask(id).catch(({ message }) => openSnackbar(message))
  }

  const result = task.result ? "Yes!" : "No :("
  return (
    <Card className={classes.card}>
      <CardContent>
        <Typography variant="subheading">
          Is { task.input } a prime number?
        </Typography>
        { task.resultVisible && (
          <Typography variant="headline" component="h3">
            {result}
          </Typography>
        )}
        { task.runnable && (
          <Button variant="raised" color="secondary" size="small" onClick={() => run(task.id)}>
            Run
          </Button>
        )}
      </CardContent>
    </Card>
  )
}

const mapDispatchToProps = dispatch => ({
  openSnackbar: message => dispatch(openSnackbar(message))
})

const styles = () => ({
  card: {
    marginLeft: 10,
    marginRight: 10,
    marginBottom: 10
  }
})

export default connect(null, mapDispatchToProps)(withStyles(styles)(Task))