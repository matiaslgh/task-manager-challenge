import React from 'react'
import { AppBar, Typography, Toolbar } from 'material-ui'

const Header = () => (
  <AppBar>
    <Toolbar>
      <Typography variant="title" color="inherit" noWrap>
        Task Manager
      </Typography>
    </Toolbar>
  </AppBar>
)

export default Header