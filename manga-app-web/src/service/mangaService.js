import axios from 'axios'

const base_url_home = 'http://localhost:8085/Manga/Home';

export const listMangas = () => axios.get(base_url_home);