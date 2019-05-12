import React from 'react'
import { connect } from 'react-redux'
import { Paper, Typography, Divider } from 'material-ui'
import { withStyles } from 'material-ui/styles'
import Task from './Task'

const StatusColumn = props => {
  const { title, statusFilter, tasks, classes } = props
  return (
    <Paper className={classes.root} elevation={4}>
      <Typography variant="headline" component="h3">
        { title }
      </Typography>
      <Divider className={classes.divider}/>
      { tasks.filter( ({ statusId }) => statusFilter === statusId ).map( task => (
        <Task key={task.id} task={task} />
      ))}
    </Paper>
  )
}

const mapStateToProps = ({ tasks }) => ({
  tasks
})

const styles = theme => ({
  root: {
    paddingTop: 16,
    paddingRight: '0 !important',
    paddingBottom: 16,
    paddingLeft: '0 !important',
    flexGrow: 1,
    marginTop: theme.spacing.unit * 3,
    marginRight: 50,
    marginLeft: 50,
    minHeight: 'calc(100vh - 170px)',
    width: 1
  },
  divider: {
    marginTop: 15,
    marginBottom: 15
  }
})

export default connect(mapStateToProps, null)(withStyles(styles)(StatusColumn))