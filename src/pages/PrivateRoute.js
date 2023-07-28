import React from 'react';
import { Route, Navigate } from 'react-router-dom';

const PrivateRoute = ({ element, ...rest }) => {
  const isAuthenticated = localStorage.getItem('auth') !== null;

  return isAuthenticated ? (
    <Route {...rest} element={element} />
  ) : (
    <Navigate to="/login" replace />
  );
};

export default PrivateRoute;
