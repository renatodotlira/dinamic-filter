INSERT INTO oauth_client_details(
	client_id,
	resource_ids,
	client_secret,
	scope,
	authorized_grant_types,
	authorities,
	access_token_validity,
	refresh_token_validity)
VALUES ('xiba', 'xiba',
    /*xib@1234*/'$2a$10$yR5XIGzrLtC4hwGAlNuj4urb/pg3uC.V/WJ2v3KTRJwgAuI2N7APW',
    'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 600, 1200);

INSERT INTO oauth_authority (name) VALUES('ROLE_COUNTRY_FIND_ALL');



INSERT INTO oauth_user(account_expired, account_locked, credentials_expired, enabled, password, user_name)
VALUES(0, 0, 0, 1, /*1234*/'$2a$10$DF/dGAJV48aKhmfuJzvR3eXZnEL3uxNAmRDiavOjQWE1ZOFcrF52K', '98990578200');

INSERT INTO oauth_users_authorities
(user_id, authority_id) VALUES (1, 1);