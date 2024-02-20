import axios from 'axios'

const base_url_signin = 'http://localhost:8085/User/auth/register';

const base_url_login = 'http://localhost:8085/User/auth/login';

export const saveUser = (user) => axios.post(base_url_signin , user);

export const authenticate = (user) => axios.post(base_url_login , user);