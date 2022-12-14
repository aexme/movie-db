import React, {useEffect, useState, Fragment} from 'react'
import { Link } from 'react-router-dom'

function GenresFunc(props) {
    const [genres, setGenres] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(props.backend + "/v1/genres")
        .then((response) => {
        if (response.status !== 200) {
            setError("Invalid response code: ", response.status);
        } else {
            setError(null);
        }
            return response.json();
        })
        .then((json) => {
            setGenres(json.genres);
        });
    }, [props.backend])

    if (error !== null) {
        return <div>Error: {error.message}</div>
    }
    return (
        <Fragment>
          <h2>Genres</h2>

          <div className="list-group">
            {genres.map((m) => (
              <Link
                key={m.id}
                className="list-group-item list-group-item-action"
                to={{
                  pathname: `/genre/${m.id}`,
                  genreName: m.genre_name,
                }}
              >
                {m.genre_name}
              </Link>
            ))}
          </div>
        </Fragment>
    );
}

export default GenresFunc;