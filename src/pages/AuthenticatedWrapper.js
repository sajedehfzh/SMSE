import React from 'react';
import { Navigate } from 'react-router-dom';

const AuthenticatedWrapper = ({ children }) => {
  const isAuthenticated = localStorage.getItem('auth') !== null;

  return isAuthenticated ? children : <Navigate to="/login" replace />;
};

export default AuthenticatedWrapper;
