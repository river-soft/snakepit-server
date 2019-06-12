import axios from 'axios';

export function getFinalUser() {
    return axios.get('/api/finalResults')
}

