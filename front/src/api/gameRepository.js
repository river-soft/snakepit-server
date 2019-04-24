import axios from 'axios';

export function gameMap() {
    return axios.get('/api/game')
}
