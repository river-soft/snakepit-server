import axios from "axios";

export function HistoryMatches() {
    return axios.get('/api/game/rounds')
}