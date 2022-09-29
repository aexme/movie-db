import React, {useEffect, useState, Fragment} from 'react'
import { Link, useParams} from "react-router-dom"

function OneGenreFunc(props) {
    let [movies, setMovies] = useState([]);
    const [error, setError] = useState(null);
    let [genreName, setGenreName] = useState("");
    const {genreId} = useParams();

    useEffect(() => {
        fetch(props.backend + "/v1/movies/" + genreId)
      .then((response) => {
        if (response.status !== 200) {
          setError("Invalid response: ", response.status);
        } else {
            setError(null);
        }
        return response.json();
      })
      .then((json) => {
          console.log("Value", props.location.genreName);
        setGenreName(props.location.genreName);
        setMovies(json.movies);
      });
    }, [genreId, props.location.genreName, props.backend]);

    if (!movies) {
        movies = [];
    }

    if (error !== null) {
        return <div>Error: {error.message}</div>;
    } else {
        return (
            <Fragment>
              <h2>Genre: {genreName}</h2>
              <div className="list-group">
                {movies.map((m) => (
                  <Link
                    key={m.id}
                    to={`/movies/${m.id}`}
                    className="list-group-item list-group-item-action"
                  >
                    {m.title}
                  </Link>
                ))}
              </div>
            </Fragment>
          );
    }
}

export default OneGenreFunc;