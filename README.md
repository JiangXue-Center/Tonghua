# 同画

## 项目背景

同画旨在为AI绘画内容创作者提供一个学习、交流平台。

我们明白AI绘画创作者社群的需求，因此，同画以用户为中心，持续关注画家们的实际需求。我们不仅提供一个平台共创作者们自由展示和分享他们的作品，还通过强大的AI绘画工具，助力他们在创作过程中突破创新的边界。

同画的使命是让每位创作者都能够在这个平台上找到灵感、获得认可，并在艺术世界中闪耀。我们将持续优化和升级我们平台功能，致力于使创作者们的创作之路更加智能化、便捷化，帮助他们实现艺术梦想的新高度。

## 开发环境

- Java Development Kit 21
- Apache Maven 3.9.6
- SpringBoot 3.1.4
- SpringCloud 2022.0.4
- SpringCloud-alibaba 2022.0.0.0
- Nacos 2.2.0
- Mysql 8.0.23
- Redis 7.2.3
- RabbitMQ 3.12
- MongoDB 4.4.14
- MinIO release

## 项目模块描述

- 网关模块，主要作用是通过AuthFilter实现请求的身份验证和权限控制，确保只有具有有效访问令牌的用户能够访问受保护的资源，并利用JWT和Redis缓存来提高验证效率；同时，通过WebSocketFilter处理WebSocket请求，将其适配为HTTP(s)请求以与后端服务通信，确保网关在处理WebSocket请求时的正确性和适配性。
- 鉴权模块，主要提供了用户登录、注册功能的接口，以及发送和校验验证码的接口。用户可以通过  登录并获取访问令牌，通过注册并获取注册结果。此外，提供了 发送验证码和校验验证码的功能，确保了用户身份验证的安全性。
- 作品模块，主要提供了两类功能：一类是获取艺术品信息的接口，包括根据关键词搜索艺术品、获取艺术品详情等；另一类是文件上传功能，允许用户上传照片并获取照片链接。
- 消息模块，实现了发送消息的功能，并在发送前将消息保存到数据库。此外还有获取私信消息和消息列表的接口，分别用于获取与指定好友的私信消息列表以及用户的消息列表。这些功能为消息模块提供了完整的接口支持，能够满足用户的消息通讯需求。
- 库存模块，提供了一系列功能的接口：根据关键词搜索商品、获取商品详情信息、获取推荐的商品索引列表分页以及更新商品库存信息。通过这些接口，用户可以方便地查询和管理商品信息，同时实现了库存的管理和更新操作，满足了库存模块的业务需求。
- 订单模块，待完善。
- 支付模块，待完善。
- AI绘画模块，提供了一个接口 `/painting`，用于接收绘画提示并调用AI绘画服务生成绘画作品，最后返回作品的图片链接给客户端。
- 用户模块，提供了一系列功能的接口，包括根据邮箱或手机号获取用户信息、注册新用户、绑定邮箱或手机号、关注用户、获取指定用户列表的简要信息以及获取当前用户的关注列表和粉丝列表等功能，为用户管理和交互提供了完整的支持。
