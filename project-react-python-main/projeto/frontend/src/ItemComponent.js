import React from "react";

export default function ItemComponent(props) {
    const status = props.status;
    return <li>{props.name} <div>Status: {status ? <div>Completo</div> : <div>Nao finalizado</div>}</div></li>
}