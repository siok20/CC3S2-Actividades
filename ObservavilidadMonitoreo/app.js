// app.js - ejemplo básico de instrumentación en Node.js
const express = require('express');
const app = express();
const promClient = require('prom-client');

const register = new promClient.Registry();
promClient.collectDefaultMetrics({ register });

// Métrica personalizada - conteo de peticiones
const httpRequestCounter = new promClient.Counter({
  name: 'http_requests_total',
  help: 'Total de requests HTTP',
  labelNames: ['method', 'status'],
});
register.registerMetric(httpRequestCounter);

app.get('/', (req, res) => {
  httpRequestCounter.inc({method: 'GET', status: '200'});
  res.send('Hello, world!');
});

// Endpoint de métricas
app.get('/metrics', async (req, res) => {
  res.set('Content-Type', register.contentType);
  res.end(await register.metrics());
});

app.listen(3000, () => console.log('App listening on port 3000'));