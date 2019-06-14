
CREATE TABLE oauth_access_token (
	token_id nvarchar(255),
	token nvarchar(2000),
	authentication_id nvarchar(255) NOT NULL,
	user_name nvarchar(255),
	client_id nvarchar(255),
	authentication nvarchar(2000) NOT NULL,
	refresh_token nvarchar(2000)
);
/*
CREATE TABLE peroauthdb.dbo.oauth_approvals (
	userid nvarchar(255),
	clientid nvarchar(255),
	[scope] nvarchar(255),
	status nvarchar(10),
	expiresat datetime2(7),
	lastmodifiedat datetime2(7)
)

*/

CREATE TABLE oauth_client_details (
	client_id nvarchar(255) NOT NULL,
	resource_ids nvarchar(255),
	client_secret nvarchar(255),
	[scope] nvarchar(255),
	authorized_grant_types nvarchar(255),
	web_server_redirect_uri nvarchar(255),
	authorities nvarchar(255),
	access_token_validity int,
	refresh_token_validity int,
	additional_information nvarchar(4000),
	autoapprove nvarchar(255),
	CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id)
)

/*
CREATE TABLE oauth_client_token (
	token_id nvarchar(255),
	token varbinary,
	authentication_id nvarchar(255) NOT NULL,
	user_name nvarchar(255),
	client_id nvarchar(255),
	CONSTRAINT oauth_client_token_pkey PRIMARY KEY (authentication_id)
)
*/

/*
CREATE TABLE oauth_code (
	code nvarchar(255),
	authentication varbinary
)
*/


CREATE TABLE oauth_refresh_token (
	token_id nvarchar(255),
	token nvarchar(2000),
	authentication nvarchar(2000)
);

CREATE TABLE oauth_user (
	id bigint NOT NULL IDENTITY(1,1),
	account_expired bit,
	account_locked bit,
	credentials_expired bit,
	enabled bit,
	password nvarchar(255),
	user_name nvarchar(255),
	CONSTRAINT oauth_user_pkey PRIMARY KEY (id)
)

CREATE TABLE oauth_authority (
	id bigint NOT NULL IDENTITY(1,1),
	name nvarchar(255),
	CONSTRAINT oauth_authority_pkey PRIMARY KEY (id)
)

CREATE TABLE oauth_users_authorities(
	user_id bigint NOT NULL,
	authority_id bigint NOT NULL,
	CONSTRAINT oauth_users_authorities_pkey PRIMARY KEY (user_id,authority_id),
	CONSTRAINT oauth_users_authorities_authority_id_fkey FOREIGN KEY (authority_id) REFERENCES peroauthdb.dbo.oauth_authority(id),
	CONSTRAINT oauth_users_authorities_user_id_fkey FOREIGN KEY (user_id) REFERENCES peroauthdb.dbo.oauth_user(id)
)
