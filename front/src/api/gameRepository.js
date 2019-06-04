import axios from 'axios';

export function gameMap() {
    return axios.get('/api/game')
}
export function HistoryMatches() {
    return axios.get('/api/game/rounds')
}
