const express = require('express');
const { Reader } = require('@maxmind/geoip2-node');
const path = require('path');

const app = express();
const port = 3000;  // 你可以根据需要修改端口

// 加载 GeoLite2-City.mmdb 数据库
const dbPath = path.join(__dirname, 'GeoLite2-City.mmdb');  // 确保数据库路径正确
let reader;

// 异步加载数据库
Reader.open(dbPath).then((dbReader) => {
  reader = dbReader;
  console.log('GeoLite2-City.mmdb 数据库加载成功');
}).catch((err) => {
  console.error('加载数据库失败:', err);
});

// 中间件：从请求头中提取真实的客户端 IP 地址
function getRealIp(req) {
  // 从 X-Forwarded-For 头部中提取真实 IP 地址
  const xForwardedFor = req.headers['x-forwarded-for'];
  if (xForwardedFor) {
    // 可能有多个 IP 地址，通过逗号分隔，取第一个作为真实 IP
    const ips = xForwardedFor.split(',');
    return ips[0].trim();
  }
  // 如果没有 X-Forwarded-For 头部，则使用远程地址
  return req.connection.remoteAddress || req.socket.remoteAddress;
}

// 定义接口：获取 IP 地址的地理位置信息
app.get('/geoip', async (req, res) => {
  const ip = getRealIp(req);
  if (!reader) {
    // 如果数据库还没有加载完成
    return res.status(500).json({ message: 'GeoIP 数据库未加载完成' });
  }

  try {
    // 查询地理位置信息
    const geo = reader.city(ip);

    if (geo) {
      // 返回地理位置信息
      res.json({
        ip: ip,
        country: geo.country.isoCode,
        countryName: geo.country.names.en,
        region: geo.subdivisions ? geo.subdivisions[0].names.en : null,
        city: geo.city ? geo.city.names.en : null,
        postalCode: geo.postal ? geo.postal.code : null,
        longitude: geo.location ? geo.location.longitude : null,
        latitude: geo.location ? geo.location.latitude : null,
      });
    } else {
      // 如果未找到地理位置信息
      res.status(404).json({
        message: '未找到该IP的地理位置信息',
        ip: ip,
      });
    }
  } catch (error) {
    console.error('查询失败:', error);
    res.status(500).json({ message: '查询地理位置信息失败' });
  }
});

// 启动服务器
app.listen(port, () => {
  console.log(`服务器正在运行在 http://localhost:${port}`);
});
