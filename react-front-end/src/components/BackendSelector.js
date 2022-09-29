import React from 'react'

export default function BackendSelector(props) {

    function handleChange(evt) {
        props.handleBackendChange(evt.target.value);
    }

    return (
        <select onChange={handleChange}>
            <option value="http://localhost:4000" defaultValue>go</option>
            <option value="http://localhost:9000">java</option>
        </select>
    );  
}