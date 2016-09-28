# Server Banner

This [Liferay](https://github.com/liferay/liferay-portal) plugin permanently display a configurable message on a sticky banner a top of the page.

## Installation

Get the latest `.war` [here](https://github.com/slemarchand/server-banner/releases) and drop it in the `deploy` directory of your Liferay server. 

## Configuration

Message, visual details and display restrictions rules for the banner are configurable, using portal properties.

### Portal properties

The following portal properties define the behavior of the banner. In order to adapt the banner to your needs, you could override them (usualy in your `portal-ext.properties` file).

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
