/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

const webpack = require('webpack');
const buildFolder = `${__dirname}/build`;
const buildName = 'analytics-all-min.js';

const devConfig = {
	devtool: 'inline-source-map',
	mode: 'development',
	optimization: {
		minimize: false,
	},
	watch: true,
};

const prodConfig = {
	mode: 'production',
	optimization: {
		minimize: true,
	},
};

const config = {
	entry: [
		'core-js/fn/array/from',
		'core-js/fn/array/find',
		'core-js/es6/symbol',
		'core-js/fn/promise',
		'whatwg-fetch',
		'./src/analytics.js',
	],
	module: {
		rules: [
			{
				exclude: /(node_modules)/,
				test: /\.js$/,
				use: {
					loader: 'babel-loader',
					options: {
						compact: false,
					},
				},
			},
		],
	},
	output: {
		filename: buildName,
		path: buildFolder,
	},
	plugins: [new webpack.optimize.ModuleConcatenationPlugin()],
};

module.exports = env => {
	return env === 'development'
		? {
				...config,
				...devConfig,
		  }
		: {
				...config,
				...prodConfig,
		  };
};
