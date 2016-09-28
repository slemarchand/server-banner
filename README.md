# Server Banner

## Installation

Get the latest `.war` here and drop it in the `deploy` directory of your Liferay server. 

## Configuration

### Portal properties

```
##
## Server Banner
##

	#
	# Set this to true to enable display of server banner.
	#
	server.banner.enabled=true
	
	#
	# The message displayed by the banner. Some special variables can be 
	# included.
	#
	server.banner.message=${hostName}
	
	#
	# The color of banner text, as a CSS color.
	#
	server.banner.color=#fff
	
	#
	# The color of banner background, as a CSS color.
	#
	server.banner.background.color=#335C7D
	
	#
	# The size of the banner. Accepted values are "small", "medium" or "large".
	#
	server.banner.size=medium

	#
	# Input a list of comma delimited theme ids. The server banner will be 
	# displayed only for these themes.
	#
	#restricted.theme.ids=controlpanel,classic
	
	#
	# Input a list of comma delimited role names. The server banner will be 
	# displayed only for these roles.
	#
	#restricted.roles=Administrator,Site Administrator
```

### Special variables

The following special variables can be used inside the message :

* `jvmRoute`
* `serverName`
* `serverPort`
* `hostName`
* `emailAddress`
* `screenName`
