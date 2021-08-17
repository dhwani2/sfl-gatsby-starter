import * as React from "react"
import { Link } from "gatsby"

import Layout from "../components/layout"
import Seo from "../components/seo"

const SecondPage = () => (
  <Layout>
    <Seo title="Page two" />

    <p>
      <Link to="/">Home</Link> <tab></tab>
      <Link to="/page-2/">Page 2</Link> <tab></tab>
      <Link to="/using-typescript/">Go to using TypeScript</Link>
    </p>
    <h1>Hi from the second page</h1>
    <p>Welcome to page 2</p>
    <p>test test</p>
  </Layout>
)

export default SecondPage
